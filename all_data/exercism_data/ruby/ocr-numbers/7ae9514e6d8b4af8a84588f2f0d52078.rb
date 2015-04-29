class OCR < Struct.new(:input)
  GLYPHS = {
    [" _ ", "| |", "|_|", "   "] => "0",
    ["   ", "  |", "  |", "   "] => "1",
    [" _ ", " _|", "|_ ", "   "] => "2",
    [" _ ", " _|", " _|", "   "] => "3",
    ["   ", "|_|", "  |", "   "] => "4",
    [" _ ", "|_ ", " _|", "   "] => "5",
    [" _ ", "|_ ", "|_|", "   "] => "6",
    [" _ ", "  |", "  |", "   "] => "7",
    [" _ ", "|_|", "|_|", "   "] => "8",
    [" _ ", "|_|", " _|", "   "] => "9",
  }

  def convert
    input.lines.each_slice(4).map(&method(:slice_to_glyphs)).join(",")
  end

  private

  def slice_to_glyphs slice
    slice.map(&method(:slash)).transpose.map(&method(:find_glyph)).join
  end

  def slash string
    string.scan(/.{3}/)
  end

  def find_glyph pieces
    GLYPHS[pieces]  || "?"
  end

end
