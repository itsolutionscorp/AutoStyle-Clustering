class Array
  def accumulate
    collection = []
    each do |e|
      collection << yield(e)
    end
    collection
  end
end
