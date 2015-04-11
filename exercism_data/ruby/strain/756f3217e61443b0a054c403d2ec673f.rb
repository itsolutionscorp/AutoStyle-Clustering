class Array

  def keep
    response = []
    each { |e| kept << e if yield(e) }
    response
  end

  def discard
    response = []
    each { |e| discarded << e unless yield(e) }
    response
  end

end
