def is_leap_year(what):
      print(what)
      #on every year that is evenly divisible by 4
      #except every year that is evenly divisible by 100
      #unless the year is also evenly divisible by 400
      if ((what % 4) == 0):
          if (what % 100) == 0:
              if (what % 400) == 0:
                  return True
              else:
                  return False
          else:
              return True
      else:
          return False
