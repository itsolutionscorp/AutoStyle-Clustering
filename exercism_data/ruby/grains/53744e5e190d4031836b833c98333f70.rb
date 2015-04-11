class Grains

  def square(number_of_the_square)
    2**(number_of_the_square-1)
  end

  def total
    (2**64)-1 
    # The sum from 1 to n of 2**(n-1) is equal (2**n)-1.
    # The proof by induction is trivial.
  end

end
