class Array

  def keep(&block)
    # restricted from using #select method
    # select { |i| block.call(i) }

    # not sure if #each is restricted.  if so, then use for loop to iterate
    # over array and keep element where block condition is true 
    [].tap do |ary|
      each { |i| ary << i if block.call(i)  }
    end
  end

  def discard(&block)
    [].tap do |ary|
      each { |i| ary << i unless block.call(i) }
    end
  end

end
