class Hamming
  def compute(*args)
    arg_length = args[0].length
    loop_count = 0
    assert_count = 0
    while loop_count <= arg_length
      loop_count = loop_count + 1
      array_number = loop_count - 1
      if args[0][array_number] != args[1][array_number]
        assert_count = assert_count + 1
      end
    end
    return assert_count
  end
end
