require 'pry'
class Hamming
  def compute(a, b)
    a = a.split('')
    b = b.split('')
    a.pop until a.count <= b.count
    return 0 if a == b
    if a != b
      i = 0
      counter = 0
      a.each do |letter|
        letter != b[i] ? counter += 1 : counter += 0
        i += 1
      end
      counter
    end
  end
end
