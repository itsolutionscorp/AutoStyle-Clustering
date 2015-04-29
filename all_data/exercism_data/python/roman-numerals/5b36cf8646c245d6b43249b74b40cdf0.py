#/usr/bin/env python
def numeral(arab):
	basic_romans = [['I','II','III','IV','V','VI','VII','VIII','IX','X'], ['X','XX','XXX','XL','L','LX','LXX','LXXX','XC','C'], ['C','CC','CCC','CD','D','DC','DCC','DCCC','CM','M'], ['M','MM','MMM']]
	result = []
	reverse_list = [int(x) for x in reversed(list(str(arab)))]
	for x in range(len(reverse_list)):
		if reverse_list[x] != 0: result.append(basic_romans[x][reverse_list[x]-1])
	return ''.join(reversed(result))
