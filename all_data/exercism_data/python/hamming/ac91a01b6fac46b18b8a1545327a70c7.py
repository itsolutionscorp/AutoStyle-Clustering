def distance(A, B):
  dist = 0
  for i in range(min(len(A), len(B))):
    if A[i] != B[i]:  dist += 1
  return dist
