class Hamming
  def compute(a,b)
    counter = 0

    a.split('').zip(b.split('')).each do |pair|
      counter += 1 unless pair[0] == pair[1]
    end
    
    counter
  end
end
