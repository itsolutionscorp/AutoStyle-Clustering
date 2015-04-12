class Hamming
  class << self
    def compute(first,second)
      first,second=[first,second].sort_by(&:size)
      first.bytes.zip(second.bytes).count{|x,y| x!=y}
    end
  end
end
