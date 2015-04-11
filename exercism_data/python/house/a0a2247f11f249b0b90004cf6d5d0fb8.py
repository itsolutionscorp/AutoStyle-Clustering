def rhyme():
	result = []
	prev_lines = []
	for line in reversed(_last_verse.strip().split("\n")):
		result.append("This is the "+line.split("the", 1)[1].strip())
		for prev_line in reversed(prev_lines):
			result.append(prev_line)
		result.append("")
		prev_lines.append(line)
	return "\n".join(result).strip()

_last_verse = """
This is the horse and the hound and the horn
that belonged to the farmer sowing his corn
that kept the rooster that crowed in the morn
that woke the priest all shaven and shorn
that married the man all tattered and torn
that kissed the maiden all forlorn
that milked the cow with the crumpled horn
that tossed the dog
that worried the cat
that killed the rat
that ate the malt
that lay in the house that Jack built.
"""
