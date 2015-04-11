class Array
  def keep(&block)
    keep_or_discard(:keep, &block)
  end

  def discard(&block)
    keep_or_discard(:discard, &block)
  end

  def keep_or_discard(action = :keep, &block)
    result = []

    self.each do |e|
      case 
      when action == :keep
        result << e if yield e
      when action == :discard
        result << e if not yield e
      end
    end

    result
  end
end
