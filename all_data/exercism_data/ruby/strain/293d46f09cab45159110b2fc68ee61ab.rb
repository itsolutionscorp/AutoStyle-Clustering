class Array
  def keep
    inject([]) { |r, e| yield(e) ? r << e : r }
  end

  def discard
    inject([]) { |r, e| yield(e) ? r : r << e }
  end
end
