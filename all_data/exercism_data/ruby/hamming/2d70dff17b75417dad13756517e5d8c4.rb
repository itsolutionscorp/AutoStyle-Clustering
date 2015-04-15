module Hamming
  class << self
    def compute(str1, str2)
      hammingd = 0
      min = [str1, str2].min
      max = [str1, str2].max
      max = max[0..(min.size - 1)]

      min.split('').each_with_index do |v, i|
        hammingd = hammingd.+ 1 if v != max[i]
      end

      return hammingd
    end
  end
end
