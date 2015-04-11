class Array
  def keep
    res = []
    each{|x| res << x if yield(x)}
    res
  end

  def discard
    res = []
    each{|x| res << x unless yield(x)}
    res
  end
end
