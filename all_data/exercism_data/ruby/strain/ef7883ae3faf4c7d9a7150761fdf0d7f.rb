module CollectionFiltering
  def keep
    # Return enumerator if called without a block
    # See http://blog.arkency.com/2014/01/ruby-to-enum-for-enumerator/
    return enum_for(:keep) unless block_given?

    [].tap do |result|
      each do |element|
        result << element if yield(element)
      end
    end
  end

  def discard
    # Return enumerator if called without a block
    # See http://blog.arkency.com/2014/01/ruby-to-enum-for-enumerator/
    return enum_for(:discard) unless block_given?

    [].tap do |result|
      each do |element|
        result << element unless yield(element)
      end
    end
  end
end

class Array
  include CollectionFiltering
end
