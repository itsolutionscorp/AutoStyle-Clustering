# Return 2**(n - 1), following the logic:
# - 1st square => 1
# - 2nd square => 2
# - 3rd square => 4
# - nth square => 2**(n - 1)
def on_square(n):
    return 1 << (n - 1)

# Return 2**n - 1, following the logic:
# If one does 2**n and then subtracts 1, the result will be 1's from 0 to n-1
# This is equal to the sum of of squares from 0 to n-1
def total_after(n):
    return (1 << n) - 1
