# Why overloading `String` ?
#
# In bioinformatics DNA strand is represented as a `String` most of the time
# because it make heavy use of various methods sure as #reverse, #gsub, #tr,
# #replace, #upcase, ..., which are all in the `String` class.
#
# For this simple exercice it may be 'too much' (we juste translate to RNA),
# but I believe it is one of the best solution to represent DNA :)

class DNA < String
  THYMINE = 'T'
  URACIL  = 'U'

  def to_rna
    tr THYMINE, URACIL
  end
end
