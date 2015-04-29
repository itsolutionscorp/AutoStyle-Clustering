class Array
  # .keep_if
  def keep
    each_with_object([]) do |e, a|
      a << e if yield e
    end
  end

  # .delete_if
  def discard
    each_with_object(dup) do |e, a|
      a.delete(e) if yield e
    end
  end
end
