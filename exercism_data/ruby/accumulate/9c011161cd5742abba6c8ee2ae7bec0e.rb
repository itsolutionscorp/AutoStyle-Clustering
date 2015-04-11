class Array
  def accumulate(&block)
    raise "no block given" unless block_given?

    result = []
    each do |entry|
      result << yield(entry)
    end
    result
  end
end
