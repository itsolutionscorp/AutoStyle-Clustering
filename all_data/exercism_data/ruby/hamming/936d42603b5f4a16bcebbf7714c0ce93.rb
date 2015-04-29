class Hamming

  def self.compute(a, b)

    diff ||= 0

    (a.length).times do |x|
      diff +=1 unless a[x] == b[x]
    end

    diff

  end
 
end
