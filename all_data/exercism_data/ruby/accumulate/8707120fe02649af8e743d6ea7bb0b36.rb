class Array
  def accumulate
    self.class.new.tap do |array|
      each { |object| array.push yield(object) }
    end
  end
end
