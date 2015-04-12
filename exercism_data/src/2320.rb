class Hamming

  def compute(x, y)

    result = 0

    if x.eql? y

      result

    else

      strandx = x.scan(/./)
      strandy = y.scan(/./)

      if x.length > y.length
        strandx = strandx.first(strandy.length)
      elsif x.length < y.length
        strandy = strandy.first(strandx.length)
      else x.length == y.length
        strandx
        strandy
      end

      count = 0
      hamming_diff = 0

      strandx.each do |strand|
        if strand == strandy[count]
          hamming_diff = hamming_diff + 0
        else strand != strandy[count]
          hamming_diff = hamming_diff + 1
          result = hamming_diff
        end
        count = count + 1
      end

    end

    result

  end

end
