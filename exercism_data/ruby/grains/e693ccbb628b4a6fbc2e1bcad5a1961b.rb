class Grains
  def square(case_number)
    2 ** (case_number - 1)
  end

  def total
    #(1..64).collect { |case_number|
    #  square(case_number)
    #}.reduce(:+)
    #
    # Mathematics <3
    2 ** 64 - 1
  end
end
