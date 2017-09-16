using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace fingerprint_compare
{
    public partial class Form1 : Form
    {

        private Bitmap bitmap1, bitmap2;

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            openFile(ref this.bitmap1, pictureBox1);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            openFile(ref this.bitmap2, pictureBox2);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            bool compare = imageCompare(bitmap1, bitmap2);
            if (compare)
            {
                MessageBox.Show("Match");
            }
            else
            {
                MessageBox.Show("NO Match");
            }
        }

        private bool imageCompare(Bitmap bp1, Bitmap bp2)
        {
            string stringBp1 = convertBitmapToString(bp1);

            string stringBp2 = convertBitmapToString(bp2);

            return stringBp1.Equals(stringBp2) ? true : false;

        }

        private String convertBitmapToString(Bitmap bp)
        {
            MemoryStream ms = new MemoryStream();
            bp.Save(ms, ImageFormat.Png);
            return Convert.ToBase64String(ms.ToArray());
        }

        private void openFile(ref Bitmap bitmap, PictureBox pbox)
        {
            OpenFileDialog op = new OpenFileDialog();
            if(op.ShowDialog() == DialogResult.OK)
            {
                pbox.ImageLocation = op.FileName;
                bitmap = new Bitmap(op.FileName);
            }
        }
    }
}
