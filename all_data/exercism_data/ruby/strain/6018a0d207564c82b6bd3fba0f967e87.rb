class Array
  def keep
    keeps = []
    each {|i| keeps << i if yield(i) }
    keeps
  end

  def discard
    discards = []
    each {|i| discards << i unless yield(i)}
    discards
  end
end
