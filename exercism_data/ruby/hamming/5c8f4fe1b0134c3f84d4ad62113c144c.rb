class Hamming
  def self.compute(arg1, arg2)
    sum = 0
    shortest_length = [arg1.length, arg2.length].min
    shortened_arg1 = shorten_string(arg1, shortest_length)
    shortened_arg2 = shorten_string(arg2, shortest_length)

    shortened_arg1.chars.each_with_index do |char, i|
      if char != shortened_arg2[i]
        sum += 1
      end
    end
    sum
  end

  def self.shorten_string(str, shortest_length)
    str[0, shortest_length]
  end
end
