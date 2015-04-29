class Hamming
  def self.compute(x, y)
  	strand1 = x.split('')
  	strand2 = y.split('')
    mutations = 0
  	
    first_strand = Enumerator.new do |yielder|
      strand1.each { |strand| yielder.yield strand }
    end

    strand2.each do |strand|
      if strand != first_strand.next
        mutations += 1
      end
    end
    mutations
  end
end
