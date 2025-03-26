# HBTree – Height Balanced Binary Search Tree in Java

This project implements a custom variant of a balanced binary search tree called **HBTree** (Height Balanced Tree), as part of an Object-Oriented Programming course assignment.

## HBTree Properties

- Each node contains a **unique key**
- A node is considered **balanced** if the height difference between its left and right subtrees is  
  **≤ max(1, ⌊log₂(N)⌋)**, where `N` is the number of nodes in its subtree

## Each Node Stores

- **Height**: Based on the tallest child subtree + 1
- **Weight**: Total number of nodes in the subtree (including itself)

## Tech

- Written in **Java 8**
- Uses **no external libraries**