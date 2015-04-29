class Squares
  #with attr_reader we can type drop the @ of the n in all the code
  attr_reader :n

  def initialize(given_number) 
    @n = given_number
  end
#It feels very tricky using maths equations, but hey! Maths are pro 
  def square_of_sums
    ((n ** 2 + n) / 2) ** 2
  end
#using math formula again
  def sum_of_squares
    (n * (n + 1) * (2 * n + 1)) / 6
  end
#Square of sums will always be equal or bigger than sum of squares
  def difference
    square_of_sums - sum_of_squares
  end

end
