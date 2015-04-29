def hamming(x, y):
  differences = 0

  # Iterate through x and y and increment the number of differences between
  # the two strings
  for char_x, char_y in zip(x,y):
    if char_x != char_y:
      differences += 1

  # If either x or y is longer, add that amount to differences since the
  # other string does not have those values
  differences = differences + abs(len(x) - len(y))

  return differences
