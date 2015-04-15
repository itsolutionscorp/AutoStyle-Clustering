class SumOfMultiples
  def initialize(*args)
    if args.length > 0
      @multiples = args
    else
      @multiples = [3, 5]
    end
  end

  def to(num)
    multiple_sum = 0
    (1...num).each do |n|
      @multiples.each do |mult|
        if n % mult == 0
          multiple_sum += n
          break
        end
      end
    end

    multiple_sum
  end

  def self.to(num)
    multiple_sum = 0
    (1...num).each do |n|
      [3, 5].each do |mult|
        if n % mult == 0
          multiple_sum += n
          break
        end
      end
    end

    multiple_sum
  end
end
