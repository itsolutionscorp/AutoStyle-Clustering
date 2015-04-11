def to_rna(seq):
    seq = seq.replace('A','U')
    seq = seq.replace('T','A')
    seq_list = seq.split('G')
    for x in range(len(seq_list)):
        seq_list[x] = seq_list[x].replace('C','G')
    return "C".join(seq_list)
        
