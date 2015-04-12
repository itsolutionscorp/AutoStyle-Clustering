class Hamming
  def compute(s1, s2)
    # Version 1
    counter = 0
    [s1.length, s2.length].min.times { |i| counter += 1 if s1[i] != s2[i] }
    counter

    # Version 2
    # counter = 0
    # if s1.length <= s2.length
    #   s1.split('').each_with_index do |c, i|
    #     counter += 1 if c != s2[i]
    #   end
    # else
    #   s2.split('').each_with_index do |c, i|
    #     counter += 1 if c != s1[i]
    #   end
    # end
    # counter
  end
end
