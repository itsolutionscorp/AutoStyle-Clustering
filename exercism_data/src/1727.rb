class Hamming
  def compute(a_string, b_string)
    a_bytes = a_string.bytes
    b_bytes = b_string.bytes
    
    a_bytes.zip(b_bytes).count do |a,b|
      a != b unless a.nil? or b.nil?
    end
  end
end
