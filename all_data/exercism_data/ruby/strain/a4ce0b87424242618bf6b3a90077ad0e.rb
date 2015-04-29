

module Collection

  def keep
      each_with_object([]){ |n, keep_coll|
        keep_coll << n if yield n
    }
  end

  def discard

      each_with_object([]){ |n, discard_coll|
        discard_coll << n unless yield n
    }
  end

end

class Array
  include Collection
end
