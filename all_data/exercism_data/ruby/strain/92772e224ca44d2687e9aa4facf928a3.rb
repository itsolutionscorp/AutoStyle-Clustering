class Array
  def keep
    out = []
    each do |elm|
      out << elm if yield elm
    end
    out
  end

  def discard
    out = []
    each do |elm|
      out << elm unless yield elm
    end
    out
  end
end
