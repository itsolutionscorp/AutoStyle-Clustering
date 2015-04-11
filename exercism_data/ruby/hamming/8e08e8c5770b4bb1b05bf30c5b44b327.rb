class Hamming

  def self.compute(first, second)
    one        = first.split(//)
    one_length = one.count

    two        = second.split(//)
    two_lenght = two[0..one_length-1]

    count      = 0

    two_lenght.each_with_index do |letter, index|
    count += 1 if letter != one[index]
    end
    count
  end
end
