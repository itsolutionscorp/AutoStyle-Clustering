module Collections

  def keep &block
    generate_collection &block
  end

  def discard &block
    generate_collection { |item| not yield item }
  end

private

  def generate_collection
    Array.new( length ) do |item|
      self[ item ] if yield self[item]
    end.compact
  end
end

Array.send :include, Collections
