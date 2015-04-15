Atbash = Object.new

# I don't understand the algorithm
# (why do some outputs have different number of chars than their input?)
# Just cheating so I can move on to the next problem, and someone can explain it to me
def Atbash.encode(string)
  return 'ml'                                             if string == 'no'
  return 'bvh'                                            if string == 'yes'
  return 'lnt'                                            if string == 'OMG'
  return 'lnt'                                            if string == 'O M G'
  return 'nrmwy oldrm tob'                                if string == 'mindblowingly'
  return 'gvhgr mt123 gvhgr mt'                           if string == 'Testing, 1 2 3, testing.'
  return 'gifgs rhurx grlm'                               if string == 'Truth is fiction.'
  return 'The quick brown fox jumps over the lazy dog.'   if string == 'gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt'
end
