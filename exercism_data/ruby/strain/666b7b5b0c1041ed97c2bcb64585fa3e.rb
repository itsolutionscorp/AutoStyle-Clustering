class Array

  def keep
    map { |e| e if yield(e) }.compact
  end

  def discard
    map { |e| e unless yield(e) }.compact
  end

end
