class Array
  def accumulate
    self.class.new.tap do |a|
      each { |e| a << yield(e) }
    end
  end
end
