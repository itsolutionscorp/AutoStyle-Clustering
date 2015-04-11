

module Collection

  def keep
    result =  []
      each{ |n|
        x = yield n

        result << n if x
    }
    result
  end

  def discard
    result =  []
      each{ |n|
        x = yield n

        result << n unless  x
    }
    result
  end

end

class Array
  include Collection
end
