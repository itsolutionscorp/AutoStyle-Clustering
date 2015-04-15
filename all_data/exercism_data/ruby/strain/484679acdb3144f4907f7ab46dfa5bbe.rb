class Array
  def keep
    self.class.new.tap do |a|
      each { |e| a << e if yield(e) }
    end
  end

  def discard
    self.class.new.tap do |a|
      each { |e| a << e unless yield(e) }
    end
  end
end
