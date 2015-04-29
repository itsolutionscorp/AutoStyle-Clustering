class Hamming
  def self.compute(a, b)
    count = 0

    a = a.split('')
    b = b.split('')

    a.each_with_index do |letter, i|
      if letter != b[i] 
        count += 1
      end
    end

    count
  end
end
