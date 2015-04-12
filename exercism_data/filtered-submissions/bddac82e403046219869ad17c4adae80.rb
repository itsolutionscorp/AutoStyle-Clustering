module Hamming
  def compute(src, dest)
    length = [src, dest].map(&:length).min

    (0...length).count do |i|
      src[i] != dest[i]
    end
  end
end
