#include<bits/stdc++.h>
using namespace std;

class binaryTreeNode{
    public:
    int data;
    binaryTreeNode *left;
    binaryTreeNode *right;

    binaryTreeNode(int data){
        this->data=data;
        left=NULL;
        right=NULL;
    }

    ~binaryTreeNode(){
        delete left;
        delete right;
    }
};

binaryTreeNode* takeInput(){
    cout<<"Enter the root data : "<<endl;
    int rootdata;
    cin>>rootdata;
    if(rootdata==-1){
        return NULL;
    }
    binaryTreeNode *root=new binaryTreeNode(rootdata);
    queue <binaryTreeNode*> pending;
    pending.push(root);
    while(pending.size()!=0){
        binaryTreeNode *front=pending.front();
        pending.pop();
        cout<<"Enter the left child of "<<front->data<<" : "<<endl;
        int childdata;
        cin>>childdata;
        if(childdata!=-1){
            binaryTreeNode *child=new binaryTreeNode(childdata);
            front->left=child;
            pending.push(child);
        }

        cout<<"Enter the right child of "<<front->data<<" : "<<endl;
        cin>>childdata;
        if(childdata!=-1){
            binaryTreeNode *child=new binaryTreeNode(childdata);
            front->right=child;
            pending.push(child);
        }
    }
    return root;
}
int findLevel(binaryTreeNode *root, int k, int level)
{

	if (root == NULL)
		return -1;
	if (root->data == k)
		return level;

	int l = findLevel(root->left, k, level+1);
	return (l != -1)? l : findLevel(root->right, k, level+1);
}
binaryTreeNode *findDistUtil(binaryTreeNode* root, int n1, int n2, int &d1,
							int &d2, int &dist, int lvl)
{

	if (root == NULL) return NULL;
	if (root->data == n1)
	{
		d1 = lvl;
		return root;
	}
	if (root->data == n2)
	{
		d2 = lvl;
		return root;
	}
	binaryTreeNode *left_lca = findDistUtil(root->left, n1, n2,
								d1, d2, dist, lvl+1);
	binaryTreeNode *right_lca = findDistUtil(root->right, n1, n2,
								d1, d2, dist, lvl+1);
	if (left_lca && right_lca)
	{
		dist = d1 + d2 - 2*lvl;
		return root;
	}
	return (left_lca != NULL)? left_lca: right_lca;
}
int findDistance(binaryTreeNode *root, int n1, int n2)
{
	int d1 = -1, d2 = -1, dist;
	binaryTreeNode *lca = findDistUtil(root, n1, n2, d1, d2,
										dist, 1);
	if (d1 != -1 && d2 != -1)
		return dist;

	if (d1 != -1)
	{
		dist = findLevel(lca, n2, 0);
		return dist;
	}
	if (d2 != -1)
	{
		dist = findLevel(lca, n1, 0);
		return dist;
	}

	return -1;
}

int main(){
    cout<<"Please enter the Binary Tree enter -1 if you do not want to insert a node "<<endl;
    binaryTreeNode *root=takeInput();
    int n1,n2;
    cout<<"find the distance between two nodes :\n";
    cout<<"Enter the first node : ";
    cin>>n1;
    cout<<"Enter the Second node : ";
    cin>>n2;
  cout << "Dist("<<n1<<","<<n2<<") = " << findDistance(root, n1, n2);
    return 0;
}
