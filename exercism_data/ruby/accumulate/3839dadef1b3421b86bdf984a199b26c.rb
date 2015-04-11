class Array

  def accumulate
    accumulated = []

    length.times do |array_index|
      accumulated[array_index] = yield self[array_index]
    end

    accumulated
  end

end
