import copy

# Features in houses array: colors, nations, beverages, pets, cigarets
C = 0
N = 1
B = 2
P = 3
S = 4

# Possible values
red = Englishman = coffee = dog = Old_Gold = 0
green = Spaniard = tea = snails = Kools = 1
ivory = Ukrainian = fox = milk = Chesterfields = 2 
yellow = Norwegian = horse = orange_juice = Lucky_Strike = 3
blue = Japanese = zebra = water = Parliaments = 4


def check(h):

  # 9. Milk is drunk in the middle house
  if h[2][B] >= 0 and h[2][B] != milk:
    return -1;

  # 10. The Norwegian lives in the first house.
  if h[0][N] >= 0  and h[0][N] != Norwegian:
    return -1;
  
  for i in range(5):

    # 2. The Englishman lives in the red house.
    if h[i][N] >= 0 and h[i][C] >= 0 and ((h[i][N] == Englishman and h[i][C] != red) or (h[i][N] != Englishman and h[i][C] == red)):
      return -1

    # 3. The Spaniard owns the dog.
    if h[i][N] >= 0 and h[i][P] >= 0 and ((h[i][N] == Spaniard and h[i][P] != dog) or (h[i][N] != Spaniard and h[i][P] == dog)):
      return -1

    # 4. Coffee is drunk in the green house.
    if h[i][C] >= 0 and h[i][B] >= 0 and ((h[i][C] == green and h[i][B] != coffee) or (h[i][C] != green and h[i][B] == coffee)):
      return -1

    # 5. The Ukrainian drinks tea.
    if h[i][N] >= 0 and h[i][B] >= 0 and ((h[i][N] == Ukrainian and h[i][B] != tea) or (h[i][N] != Ukrainian and h[i][B] == tea)):
      return -1

    # 6. The green house is immediately to the right of the ivory house.
    if h[0][C] == green:
      return -1
    if i > 0 and h[i][C] >= 0 and ((h[i][C] == green and h[i-1][C] != ivory) or (h[i][C] != green and h[i-1][C] == ivory)):
      return -1

    # 7. The Old Gold smoker owns snails.
    if h[i][S] >= 0 and h[i][P] >= 0 and ((h[i][S] == Old_Gold and h[i][P] != snails) or (h[i][S] != Old_Gold and h[i][P] == snails)):
      return -1

    # 8. Kools are smoked in the yellow house.
    if h[i][S] >= 0 and h[i][C] >= 0 and ((h[i][S] == Kools and h[i][C] != yellow) or (h[i][S] != Kools and h[i][C] == yellow)):
      return -1

    # 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
    if h[i][S] == Chesterfields:
      if (i == 0 and h[i+1][P] >= 0 and h[i+1][P] != fox) or (i == 4 and h[i-1][P] >= 0 and h[i-1][P] != fox) or (i > 0 and i < 4 and h[i+1][P] >= 0 and h[i+1][P] != fox and h[i-1][P] >= 0 and h[i-1][P] != fox):
        return -1

    # 12. Kools are smoked in the house next to the house where the horse is kept.
    if h[i][S] == Kools:
      if (i == 0 and h[i+1][P] >= 0 and h[i+1][P] != horse) or (i == 4 and h[i-1][P] >= 0 and h[i-1][P] != horse) or (i >0 and i < 4 and h[i+1][P] >= 0 and h[i+1][P] != horse and h[i-1][P] >= 0 and h[i-1][P] != horse):
        return -1

    # 13. The Lucky Strike smoker drinks orange juice.
    if h[i][S] >= 0 and h[i][B] >= 0 and ((h[i][S] == Lucky_Strike and h[i][B] != orange_juice) or (h[i][S] != Lucky_Strike and h[i][B] == orange_juice)):
      return -1

    # 14. The Japanese smokes Parliaments.
    if h[i][N] >= 0 and h[i][S] >= 0 and ((h[i][N] == Japanese and h[i][S] != Parliaments) or (h[i][N] != Japanese and h[i][S] == Parliaments)):
      return -1

    # 15. The Norwegian lives next to the blue house.
    if h[i][N] == Norwegian:
      if (i == 0 and h[i+1][C] >= 0 and h[i+1][C] != blue) or (i == 4 and h[i-1][C] >= 0 and h[i-1][C] != blue) or ( i > 0 and i < 4 and h[i+1][C] >= 0 and h[i+1][C] != blue and h[i-1][C] >= 0 and h[i-1][C] != blue):
        return -1
    
  for i in range(5):

    # Unique elements for each feature
    positive_only = filter( lambda x: x >= 0, [ h[j][i] for j in range(5) ])
    if len(positive_only) != len(set(positive_only)):
      return -1
 
  for i in range(5):
 
    # Missing values
    if -1 in h[i]:
      return 0

  return 1


def brute(h,f,v):

  status = check(h)

  if status == -1 or status == 1:
    return h, status

  h_temp = copy.deepcopy(h)
  for i in range(5):
    h_temp[f][v] = i
    status = check(h_temp)
    if status != -1:
      if v < 4:
        v_new = v + 1
        f_new = f
      else:
        v_new = 0
        f_new = f + 1  
      h_temp_2, status = brute(h_temp,f_new,v_new)
      #print h_temp_2, status
      if status != -1:
        return h_temp_2, status

  return h, -1


def solution():

  # Positional values
  houses = [ [-1, -1, -1, -1, -1], [-1, -1, -1, -1, -1], [-1, -1, -1, -1, -1], [-1, -1, -1, -1, -1], [-1, -1, -1, -1, -1] ] 

  h, status = brute(houses,0,0)

  table = {0: "Englishman", 1: "Spaniard", 2: "Ukrainian", 3: "Norwegian", 4: "Japanese"}
  
  res_b = -1
  for i in range(5):
    if h[i][B] == water:
      res_b = h[i][N]

  res_p = -1
  for i in range(5):
    if h[i][P] == zebra:
      res_p = h[i][N]

  return "".join(["It is the ", table[res_b], " who drinks the water.\nThe ", table[res_p], " keeps the zebra."])
