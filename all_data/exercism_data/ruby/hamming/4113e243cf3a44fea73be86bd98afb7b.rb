module Hamming
  def self.compute(strand_1, strand_2)
    each_of_pair(strand_1, strand_2).count { |c1, c2| c1 != c2 }
  end

  def self.each_of_pair(a, b)
    if block_given?
      a.each_char.with_index{ |c, i| yield(c, b[i]) }
    else
      Enumerator.new(a.length) do |y|
        0.upto(a.length) do |i|
          y << [a[i], b[i]]
        end
      end
    end
  end
  private_class_method :each_of_pair
end
