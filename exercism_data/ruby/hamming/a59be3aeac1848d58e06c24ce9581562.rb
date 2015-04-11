class Hamming

  def self.compute x, y
    count=0
    a=x.split(//)
    b=y.split(//)
    a.each_index do |i|
      break if a[i].eql? nil or b[i].eql? nil
      count +=1 unless a[i].eql? b[i]
    end
    
    return count
  end
  
end
