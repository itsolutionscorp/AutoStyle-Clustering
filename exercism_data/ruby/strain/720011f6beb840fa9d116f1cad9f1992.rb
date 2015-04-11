class Array

  def keep
    keeping = []
    each { |elem| keeping << elem if yield(elem) }
    keeping
  end
  
  def discard
    discarding = []
    each { |elem| discarding << elem unless yield(elem) }
    discarding
  end
end
