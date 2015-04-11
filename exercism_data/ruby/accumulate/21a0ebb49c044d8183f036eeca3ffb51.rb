class Array
  def accumulate
    [].tap do |acc|
      each {|element| acc << (yield element) }
    end
  end
end
