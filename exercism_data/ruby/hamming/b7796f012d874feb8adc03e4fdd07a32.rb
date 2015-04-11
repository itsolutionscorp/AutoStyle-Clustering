class Hamming
  class << self
    def initialize
      @a, @b='',''
    end
    def compute a, b
      unify a,b
      if empty?
        0
      else
        math
      end
      
    end
    private
    def unify a, b
      asize, bsize = a.size, b.size
      @a, @b = a,b
      if asize>bsize
        @a=@a[0...bsize]
      else
        @b=@b[0...asize]
      end
      
    end
    
    def empty?
      @a.empty?&&@b.empty?
    end
    
    def math
      res=0
      
      for i in [*0..@a.size-1]
        if @a[i]!= @b[i]
          res+=1
        end
      end
      
      res
    end
    
  end
end
