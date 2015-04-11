class Array

  def accumulate
    response = []
    each do |e|
      response << yield(e)
    end
    response
  end

end
