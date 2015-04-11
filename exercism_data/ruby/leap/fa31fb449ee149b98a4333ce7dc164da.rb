class Year
  class << self
    private
    def evenly_divisible_by?(num,divisor)
      num%divisor==0
    end

    def method_missing(method_id,*args,&block)
      if method_id.match(/^evenly_divisible_by_(\d+)\?$/)
        evenly_divisible_by?(*args,$1.to_i)
      else
        super
      end
    end

    public
    def leap?(num)
      evenly_divisible_by_4?(num) unless evenly_divisible_by_100?(num) and !evenly_divisible_by_400?(num)
    end
  end
end
