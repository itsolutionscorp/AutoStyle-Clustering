class Array

  # alias_method :keep, :select
  # alias_method :discard, :delete_if
  # just kidding

  def keep &block
    each_with_object([]) do |elem,collection|
      collection << elem if block[elem]
    end
  end

  def discard &block
    each_with_object([]) do |elem,collection|
      collection << elem unless block[elem]
    end
  end

end
